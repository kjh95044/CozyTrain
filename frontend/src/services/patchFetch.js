import getAccessToken from "@/utils/getAccessToken";

/**
 *
 * @param {string} url - URL
 * @returns {Promise}
 */
export default async function Fetch(url) {
  const accessToken = getAccessToken();

  try {
    const response = await fetch(`https://dev.cozytrain.com/api/${url}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
    });

    if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

    const responseData = await response.json();
    return responseData;
  } catch (error) {
    throw error;
  }
}
