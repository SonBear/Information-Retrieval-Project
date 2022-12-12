import React from 'react';
import { SupportedLanguage } from '../../../../models/search/spellcheck/SupportedLanguage';
import { usePreferenceLanguage } from '../../../../lib/hooks/usePreferenceLanguage';

const getSupportedLanguageValue = (language: string) => {
  return Object.values(SupportedLanguage)[
    Object.keys(SupportedLanguage).indexOf(language)
  ];
};

export const LanguagePreferenceSelector = () => {
  const { selectedLanguage, handleLanguageChanged } = usePreferenceLanguage();

  const onLanguageChanged = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const newLanguage = e.target.value;
    handleLanguageChanged(newLanguage);
  };

  return (
    <div>
      <p>Preference language:</p>
      <select value={selectedLanguage} onChange={onLanguageChanged}>
        {Object.keys(SupportedLanguage).map((language, index) => (
          <option value={getSupportedLanguageValue(language)} key={index}>
            {language}
          </option>
        ))}
      </select>
    </div>
  );
};
