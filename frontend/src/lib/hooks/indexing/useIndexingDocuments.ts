import { useRef, useState } from 'react';
import { indexDocuments } from '../../../services/documents/documents';

export const useIndexingDocuments = () => {
  const filesInputRef = useRef<HTMLInputElement>(null);
  const [filesToUpload, setFilesToUpload] = useState<File[]>([]);

  const handleFilesChosen = (files: File[]) => {
    setFilesToUpload(files);
  };

  const handleUploadFiles = () => {
    return indexDocuments(filesToUpload).then(() => {
      handleClearChosenFiles();
    });
  };

  const handleClearChosenFiles = () => {
    setFilesToUpload([]);
    // @ts-ignore
    filesInputRef.current.value = null;
  };

  return {
    filesToUpload,
    filesInputRef,
    handleFilesChosen,
    handleUploadFiles,
    handleClearChosenFiles,
  };
};
