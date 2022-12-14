import { UrlList } from './UrlList';

export interface IndexingResultStatus {
  payload: UrlList;
  nonIndexedUrlSet: UrlList;
  failedToIndexUrlSet: UrlList;
  successfullyIndexedUrlSet: UrlList;
  alreadyIndexedUrlSet: UrlList;
}
