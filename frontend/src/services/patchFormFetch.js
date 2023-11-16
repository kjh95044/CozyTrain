import getAccessToken from "@/utils/getAccessToken";

/**
 *
 * @param {string} url - URL
 * @param {Object} data - Body
 * @returns {Promise}
 */
export default async function fetchPatch(url, data) {
  const accessToken = getAccessToken();
  const formData = new FormData();
  formData.append("image", data);

  try {
    const response = await fetch(process.env.NEXT_PUBLIC_API + url, {
      method: "PATCH",
      body: formData,
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });

    if (response.status === 401) {
      const responseData = await response.json();

      const accessToken = responseData.error.message;
      document.cookie = `accessToken=${accessToken}; path=/`;

      return fetchPatch(url, data);
    }

    if (!response.ok && response.status !== 401)
      throw new Error(`HTTP error! Status: ${response.status}`);

    const responseData = await response.json();
    return responseData;
  } catch (error) {
    throw error;
  }
}
