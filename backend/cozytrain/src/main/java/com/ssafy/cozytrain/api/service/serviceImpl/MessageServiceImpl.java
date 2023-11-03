package com.ssafy.cozytrain.api.service.serviceImpl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.cozytrain.api.dto.MessageDto;
import com.ssafy.cozytrain.api.entity.ChatRoom;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Message;
import com.ssafy.cozytrain.api.repository.ChatRoomRepository;
import com.ssafy.cozytrain.api.repository.MemberRepository;
import com.ssafy.cozytrain.api.repository.MessageRepository;
import com.ssafy.cozytrain.api.service.MessageService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 s3Client;

    @Override
    @Transactional
    public Long createMessage(String memberId, MultipartFile mfile, MessageDto.MessageReqDto messageReqDto) throws IOException {
        // S3로 파일 업로드
        File uploadFile = convert(mfile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 전환 실패"));

        String uuid = UUID.randomUUID().toString();
        String fileName = memberId + "/" + uuid;
        String uploadImageUrl = putS3(uploadFile, fileName);

        removeNewFile(uploadFile);  // 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)

        // 데이터베이스 연동
        Member member = memberRepository.findByMemberLoginId(memberId).orElseThrow(() -> new NotFoundException("Not Found User"));
        ChatRoom chatRoom = chatRoomRepository.findById(messageReqDto.getChatRoomId()).orElseThrow(() -> new NotFoundException("해당 채팅방이 존재하지 않습니다."));

        Message message = Message.builder()
                .messageUrl(uploadImageUrl)
                .createdAt(LocalDateTime.now())
                .chatRoom(chatRoom)
                .senderMember(member)
                .build();

        return messageRepository.save(message).getMessageId();
    }

    @Override
    public List<MessageDto.MessageResDto> getAllMessage(String memberId, Long chatRoomId) {
        return messageRepository.getAllMessage(chatRoomId).orElseThrow(() -> new NotFoundException("메시지가 없습니다"));
    }

    // S3에 업로드
    private String putS3(File uploadFile, String fileName) {
        s3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );
        return s3Client.getUrl(bucket, fileName).toString();
    }

    // MultipartFile to File
    private Optional<File> convert(MultipartFile file) throws  IOException {
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try(FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    private void removeNewFile(File targetFile) {
        if(targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        }else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }
}
