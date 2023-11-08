import { NextResponse } from "next/server";

export default function Middleware(request) {
  if (!request.cookies.has("accessToken")) {
    return NextResponse.redirect("http://localhost:3000/login");
  }
}

// 미들웨어 실행될 URL
export const config = {
  matcher: "/",
};
