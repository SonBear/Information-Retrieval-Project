import { useEffect, useState } from 'react';
import {
  deleteUrls,
  getIndexedUrls,
  indexUrls,
  reindexUrls,
} from '../../services/urls/urls';
import { IndexingResultStatus } from '../../models/indexing-urls/IndexingResultStatus';

export enum IndexingStatus {
  INDEXING = 'Indexing...',
  REINDEXING = 'Reindexing...',
  DELETING = 'Deleting...',
  NONE = 'None',
}

export const useIndexingUrls = () => {
  const [status, setStatus] = useState(IndexingStatus.NONE);
  const [loading, setLoading] = useState(true);
  const [urls, setUrls] = useState<string[]>([]);
  const [indexingResult, setIndexingResult] = useState<IndexingResultStatus>();

  useEffect(() => {
    getIndexedUrls().then(urls => {
      setUrls(urls);
      setLoading(false);
    });
  }, []);

  const handleIndexUrls = () => {
    setStatus(IndexingStatus.INDEXING);
    indexUrls(urls).then(result => {
      setIndexingResult(result);
      setStatus(IndexingStatus.NONE);
    });
  };

  const handleReindexUrls = () => {
    setStatus(IndexingStatus.REINDEXING);
    reindexUrls(urls).then(result => {
      setIndexingResult(result);
      setStatus(IndexingStatus.NONE);
    });
  };

  const handleDeleteUrls = () => {
    setStatus(IndexingStatus.DELETING);
    deleteUrls(urls).then(result => {
      setIndexingResult(result);
      setStatus(IndexingStatus.NONE);
    });
  };

  return {
    urls,
    lastIndexingResult: indexingResult,
    setUrls,
    handleIndexUrls,
    handleDeleteUrls,
    handleReindexUrls,
    status,
    loading,
  };
};
