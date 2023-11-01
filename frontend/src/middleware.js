import { NextResponse } from "next/server";

export default function Middleware(request) {
  console.log(request.cookies.getAll());
}

// 미들웨어가 실행될 URL설정
export const config = {
  matcher: "/",
};
