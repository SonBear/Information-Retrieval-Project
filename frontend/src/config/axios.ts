import axios, { AxiosInstance, AxiosRequestConfig } from 'axios';
import { isDevEnv } from '../utils/check-env';

const createAxiosInstance = () => {
  let baseUrl = '/api';

  if (isDevEnv()) {
    baseUrl = 'http://localhost:8080';
  }

  return axios.create({
    baseURL: baseUrl,
  });
};

export default createAxiosInstance;
export type { AxiosRequestConfig, AxiosInstance };
