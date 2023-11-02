import { create } from "zustand";

const useStore = create((set) => ({
  memberName: null,
  memberProfileImg: null,
  accessToken: null,

  login: (memberName, memberProfileImg, accessToken) => {
    set({ memberName, memberProfileImg, accessToken });
  },

  logout: () => {
    set({ memberName: null, memberProfileImg: null, accessToken: null });
  },
}));

export default useStore;
