import getAccessToken from "@/utils/getAccessToken";

/**
 *
 * @param {string} url - URL
 * @param {Object} data - Body
 * @returns {Promise}
 */
export default async function multipartFetchPost(url, data) {
  const accessToken = getAccessToken();
  console.log(data);
  for (const [key, value] of data.entries()) {
    console.log(`${key}: ${value}`);
  }
  try {
    const response = await fetch("https://dev.cozytrain.com/api/" + url, {
      method: "POST",
      body: data,
      headers: {
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
