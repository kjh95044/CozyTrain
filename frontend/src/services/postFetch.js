import getAccessToken from "@/utils/getAccessToken";

/**
 *
 * @param {string} url - URL
 * @param {Object} data - Body
 * @returns {Promise}
 */
export default async function fetchPost(url, data, type = "application/json") {
  const accessToken = getAccessToken();

  try {
    const response = await fetch("https://dev.cozytrain.com/api/" + url, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": type,
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
