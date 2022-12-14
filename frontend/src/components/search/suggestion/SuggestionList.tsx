import { SuggestionResult } from '../../../models/search/suggestion/SuggestionResult';
import { ListGroup } from 'react-bootstrap';
import React from 'react';

export interface SuggestionListProps {
  suggestionResult?: SuggestionResult;
  onSuggestionClicked: (suggestion: string) => void;
}

export const SuggestionList = ({
  suggestionResult,
  onSuggestionClicked,
}: SuggestionListProps) => {
  const onSuggestionItemClicked = (event: any) => {
    const suggestion = event.target.firstChild.data;
    onSuggestionClicked(suggestion);
  };

  if (!suggestionResult || suggestionResult.suggestions.length <= 0) {
    return null;
  }

  return (
    <ListGroup style={{ top: '40px' }} className="position-absolute w-100">
      {suggestionResult.suggestions.map((suggestion, index) => {
        return (
          <ListGroup.Item
            key={index}
            role="button"
            onClick={onSuggestionItemClicked}>
            {suggestion}
          </ListGroup.Item>
        );
      })}
    </ListGroup>
  );
};
