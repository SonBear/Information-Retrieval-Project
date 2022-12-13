import { useSearchParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import {
  ReverseLanguageMapping,
  SupportedLanguage,
} from '../../../models/search/spellcheck/SupportedLanguage';
import { useHomeQueryParams } from '../useHomeQueryParams';

export const usePreferenceLanguage = () => {
  const { setQueryParam } = useHomeQueryParams();
  const [searchParams] = useSearchParams();
  const [selectedLanguage, setSelectedLanguage] = useState<SupportedLanguage>(
    SupportedLanguage.Spanish,
  );

  useEffect(() => {
    const languageParam = searchParams.get('dictionary');
    if (languageParam) {
      setSelectedLanguage(
        ReverseLanguageMapping[languageParam] || SupportedLanguage.Spanish,
      );
    }
  }, [searchParams]);

  const handleLanguageChanged = (newSelectedLanguage: string) => {
    setQueryParam('dictionary', newSelectedLanguage);
  };

  return {
    selectedLanguage,
    handleLanguageChanged,
  };
};
