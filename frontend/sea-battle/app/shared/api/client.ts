import axios from 'axios';

export const httpClient = axios.create({
    // baseURL: "https://d5d5ujno72nh9qu45pq5.sk0vql13.apigw.yandexcloud.net",
    baseURL: "/api",
    headers: { 'Content-Type': 'application/json' }
});

export const httpClientToken = axios.create({
    // baseURL: "https://d5d5ujno72nh9qu45pq5.sk0vql13.apigw.yandexcloud.net",
    baseURL: "/api",
    headers: { 'Content-Type': 'application/json' }
});

httpClientToken.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token && token !== null) config.headers.Authorization = `Bearer ${token}`;
    return config;
});