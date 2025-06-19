import { create } from 'zustand';

const getLocalStorage = (key: string): string | null => {
    if (typeof window !== 'undefined') {
        return localStorage.getItem(key);
    }
    return null;
};

const setLocalStorage = (key: string, value: string) => {
    if (typeof window !== 'undefined') {
        localStorage.setItem(key, value);
    }
};

export const useAuthStore = create<AuthState>((set, get) => ({
    token: getLocalStorage('token'),
    expiresAt: Number(getLocalStorage('expiresAt')) || null,

    setAuthData: (token, expiresAt, roles) => {
        setLocalStorage('token', token);
        setLocalStorage('expiresAt', expiresAt.toString());
        set({ token, expiresAt});
    },

    clearAuthData: () => {
        if (typeof window !== 'undefined') {
            localStorage.removeItem('token');
            localStorage.removeItem('expiresAt');
        }
        set({ token: null, expiresAt: null});
    },

    isAuthenticated: () => {
        const { token, expiresAt } = get();
        return !!token && !!expiresAt;
        // return !!token && !!expiresAt && expiresAt > Date.now();
    }
}));