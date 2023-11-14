import { NextResponse } from "next/server";

export default function Middleware(request) {
  // if (!request.cookies.has("refreshToken")) {
  if (!request.cookies.has("id")) {
    return NextResponse.redirect("http://localhost:3000/login");
  }
}

// 미들웨어 실행될 URL
export const config = {
  matcher: "/",
};
