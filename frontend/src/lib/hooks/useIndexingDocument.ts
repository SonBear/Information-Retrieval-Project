import { useRef, useState } from 'react';
import { indexDocuments } from '../../services/documents/documents';

export const useIndexingDocument = () => {
  const filesInputRef = useRef<HTMLInputElement>(null);
  const [filesToUpload, setFilesToUpload] = useState<File[]>([]);

  const handleFilesChosen = (files: File[]) => {
    setFilesToUpload(files);
  };

  const handleUploadFiles = () => {
    return indexDocuments(filesToUpload).then(() => {
      setFilesToUpload([]);
      // @ts-ignore
      filesInputRef.current.value = null;
    });
  };

  return {
    filesToUpload,
    filesInputRef,
    handleFilesChosen,
    handleUploadFiles,
  };
};