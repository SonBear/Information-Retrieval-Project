import { api } from '../http';
import { UrlList } from '../../models/indexing/urls/UrlList';
import { IndexingResultStatus } from '../../models/indexing/urls/IndexingResultStatus';

export const getIndexedUrls = async () => {
  const indexedUrls = await api.get<UrlList>('/crawler/urls');
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
