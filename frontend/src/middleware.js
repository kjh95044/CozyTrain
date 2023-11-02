import { NextResponse } from "next/server";

export default function Middleware(request) {
  // if (!request.cookies.has("accessToken")) {
  //   return NextResponse.redirect("http://localhost:3000/login");
  // }
  // if (request.cookies.get("todayFirstLogin").value === "true") {
  //   return NextResponse.redirect("http://localhost:3000/train");
  // }
}

// 미들웨어가 실행될 URL설정
export const config = {
  matcher: "/",
};
