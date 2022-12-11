import Http from './http';
import createAxiosInstance from '../../config/axios';

const api = Http(createAxiosInstance());

export { api };
