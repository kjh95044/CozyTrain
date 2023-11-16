import { create } from "zustand";

const useStore = create((set) => ({
  memberName: null,
  memberProfileImg: null,
  region: null,
  dist: null,

  login: (memberName, memberProfileImg) => {
    set({ memberName, memberProfileImg });
  },

  logout: () => {
    set({ memberName: null, memberProfileImg: null });
  },

  setLocation: (region, dist) => {
    set({ region, dist });
  },

  setMemberImg: (memberProfileImg) => {
    set({ memberProfileImg });
  },
}));

export default useStore;
