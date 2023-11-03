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
import com.ssafy.cozytrain.common.utils.S3Uploader;
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

    private final S3Uploader s3Uploader;

    @Override
    @Transactional
    public Long createMessage(String memberId, MultipartFile mfile, MessageDto.MessageReqDto messageReqDto) throws IOException {
        // S3로 파일 업로드
        String uuid = UUID.randomUUID().toString();
        String uploadImageUrl = s3Uploader.upload(mfile, memberId, uuid);

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
}
