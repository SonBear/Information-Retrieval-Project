import { useNavigate, useSearchParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import {
  ReverseLanguageMapping,
  SupportedLanguage,
} from '../../models/search/spellcheck/SupportedLanguage';
import { appendQueryParams } from '../../utils/query-params';

export const usePreferenceLanguage = () => {
  const navigate = useNavigate();
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
    searchParams.set('dictionary', newSelectedLanguage);
    navigate(appendQueryParams('/', searchParams.toString()));
  };

  return {
    selectedLanguage,
    handleLanguageChanged,
  };
};
