import { useEffect, useState } from 'react';
import {
  deleteUrls,
  getIndexedUrls,
  indexUrls,
  reindexUrls,
} from '../../services/urls/urls';
import { IndexingResultStatus } from '../../models/indexing-urls/IndexingResultStatus';

export const useIndexingURLS = () => {
  const [loading, setLoading] = useState(true);
  const [indexing, setIndexing] = useState(false);
  const [urls, setUrls] = useState<string[]>([]);
  const [indexingResult, setIndexingResult] = useState<IndexingResultStatus>();

  useEffect(() => {
    getIndexedUrls().then(urls => {
      setUrls(urls);
      setLoading(false);
    });
  }, []);

  const handleIndexUrls = () => {
    setIndexing(true);
    indexUrls(urls).then(result => {
      setIndexingResult(result);
      setIndexing(false);
    });
  };

  const handleReindexUrls = () => {
    setIndexing(true);
    reindexUrls(urls).then(result => {
      setIndexingResult(result);
      setIndexing(false);
    });
  };

  const handleDeleteUrls = () => {
    deleteUrls(urls).then(result => {
      setIndexingResult(result);
    });
  };

  return {
    urls,
    lastIndexingResult: indexingResult,
    setUrls,
    handleIndexUrls,
    handleDeleteUrls,
    handleReindexUrls,
    loading,
    indexing,
  };
};
