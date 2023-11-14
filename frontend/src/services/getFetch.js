import getAccessToken from "@/utils/getAccessToken";

/**
 *
 * @param {string} url - URL
 * @param {Object} params [params] - 쿼리 스트링(객체)
 * @returns {Promise}
 */
export default async function Fetch(url, params = {}) {
  const accessToken = getAccessToken();
  const queryParams = new URLSearchParams(params).toString();

  try {
    const response = await fetch(
      `https://dev.cozytrain.com/api/${url}${
        queryParams ? `?${queryParams}` : ""
      }`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${accessToken}`,
        },
      }
    );

    if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
    const responseData = await response.json();

    return responseData;
  } catch (error) {
    throw error;
  }
}
