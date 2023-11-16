export default function getAccessToken(token) {
  const parts = document.cookie.split("; ");

  let accessToken = null;
  for (const part of parts) {
    if (part.trim().startsWith("accessToken=")) {
      const tokenPart = part.split("=")[1];
      accessToken = tokenPart.trim();
      break;
    }
  }

  return accessToken;
}
