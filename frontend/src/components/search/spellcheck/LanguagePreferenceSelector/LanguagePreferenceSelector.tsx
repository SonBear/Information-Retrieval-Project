import React from 'react';
import { SupportedLanguage } from '../../../../models/search/spellcheck/SupportedLanguage';
import { usePreferenceLanguage } from '../../../../lib/hooks/usePreferenceLanguage';
import { Form, Stack } from 'react-bootstrap';

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
    <Stack direction="horizontal" gap={3} className="justify-content-end">
      <div>Preference language:</div>
      <Form.Select
        className="w-auto"
        value={selectedLanguage}
        onChange={onLanguageChanged}>
        {Object.keys(SupportedLanguage).map((language, index) => (
          <option value={getSupportedLanguageValue(language)} key={index}>
            {language}
          </option>
        ))}
      </Form.Select>
    </Stack>
  );
};
