import { api } from '../http';

export const indexDocuments = (files: File[]) => {
  const formData = new FormData();
  files.forEach(file => formData.append('files', file));
  return api.post('/document-indexer/upload', formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
};
