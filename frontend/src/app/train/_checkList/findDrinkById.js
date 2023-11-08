import getFetch from "@/services/getFetch";

export default async function findDrinkById(id) {
  const resp = await getFetch("caffeine/search/drink", {
    id,
  });

  return resp.response.name;
}
