import React from 'react';
import { SupportedLanguage } from '../../../../models/search/spellcheck/SupportedLanguage';
import { usePreferenceLanguage } from '../../../../lib/hooks/usePreferenceLanguage';
import { Col, Row } from 'react-bootstrap';

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
    <Row className="mb-2">
      <Col md={3}>
        <p>Preference language:</p>
      </Col>
      <Col>
        <select
          className="bg-dark rounded pr-5 pl-5 text-white"
          value={selectedLanguage}
          onChange={onLanguageChanged}>
          {Object.keys(SupportedLanguage).map((language, index) => (
            <option value={getSupportedLanguageValue(language)} key={index}>
              {language}
            </option>
          ))}
        </select>
      </Col>
    </Row>
  );
};
