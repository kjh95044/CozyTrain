import { create } from "zustand";

const useStore = create((set) => ({
  memberName: null,
  memberProfileImg: null,

  login: (memberName, memberProfileImg) => {
    set({ memberName, memberProfileImg });
  },

  logout: () => {
    set({ memberName: null, memberProfileImg: null });
  },
}));

export default useStore;
