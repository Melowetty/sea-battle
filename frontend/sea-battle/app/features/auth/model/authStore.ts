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

interface AuthState {
    token: string | null;
    expiresAt: number | null;
    firstName: string | null;
    photoUrl: string | null ;
    setAuthData: (token: string, expiresAt: number, firstName:string, photoUrl:string) => void;
    clearAuthData: () => void;
    isAuthenticated: () => boolean;
}

export const useAuthStore = create<AuthState>((set, get) => ({
    token: getLocalStorage('token'),
    expiresAt: Number(getLocalStorage('expiresAt')) || null,
    firstName: getLocalStorage('firstName'),
    photoUrl: getLocalStorage('photoUrl'),

    setAuthData: (token, expiresAt, firstName, photoUrl) => {
        setLocalStorage('token', token);
        setLocalStorage('expiresAt', expiresAt.toString());
        setLocalStorage('firstName', firstName);
        setLocalStorage('photoUrl', photoUrl);
        set({ token, expiresAt, firstName, photoUrl });
    },

    clearAuthData: () => {
        if (typeof window !== 'undefined') {
            localStorage.removeItem('token');
            localStorage.removeItem('expiresAt');
            localStorage.removeItem('firstName');
            localStorage.removeItem('photoUrl');
        }
        set({ token: null, expiresAt: null, photoUrl: null , firstName: null});
    },

    isAuthenticated: () => {
        const { token, expiresAt } = get();
        return !!token && !!expiresAt && expiresAt > Date.now();
    }
}));