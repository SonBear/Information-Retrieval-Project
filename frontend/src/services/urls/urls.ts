import { api } from '../http';

export interface URLList {
  urls: string[];
}

export interface IndexingResultStatus {
  payload: URLList;
  nonIndexedUrlSet: URLList;
  failedToIndexUrlSet: URLList;
  successfullyIndexedUrlSet: URLList;
  alreadyIndexedUrlSet: URLList;
}

export const getIndexedUrls = async () => {
  const indexedUrls = await api.get<URLList>('/crawler/urls');
  return indexedUrls.urls;
};

export const indexUrls = async (urls: string[]) => {
  return api.post<IndexingResultStatus>('/crawler/index', { urls });
};

export const reindexUrls = async (urls: string[]) => {
  return api.post<IndexingResultStatus>('/crawler/reindex', { urls });
};

export const deleteUrls = async (urls: string[]) => {
  return api.remove<IndexingResultStatus>('/crawler/urls', { urls });
};
