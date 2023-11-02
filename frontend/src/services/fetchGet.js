import getAccessToken from "@/utils/getAccessToken";

export default async function fetchGet(url, data) {
  const accessToken = getAccessToken();

  try {
    const response = await fetch("https://dev.cozytrain.com/api/" + url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
    });

    if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

    const responseData = await response.json();
    return responseData;
  } catch (error) {
    console.error("Fetch error:", error);
    throw error;
  }
}
