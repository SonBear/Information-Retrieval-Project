import React, { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import { Button, Form, InputGroup } from 'react-bootstrap';
import { SuggestionList } from '../../suggestion';
import { useSuggestions } from '../../../../lib/hooks/suggestion/useSuggestions';
import { useHomeQueryParams } from '../../../../lib/hooks/useHomeQueryParams';

export const SearchPanel = () => {
  const { setSearchQuery } = useHomeQueryParams();
  const [searchParams] = useSearchParams();
  const [query, setQuery] = useState('');
  const { suggestionResult, handleSearchQueryChanged, clearSuggestions } =
    useSuggestions();

  useEffect(() => {
    const q = searchParams.get('query') || '';
    setQuery(q);
  }, [searchParams]);

  const onQueryChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = e.target.value;
    setQuery(newValue);
    handleSearchQueryChanged(newValue);
  };

  const onSearchClicked = () => {
    setSearchQuery(query);
  };

  const onKeyDown = (event: any) => {
    if (event.key === 'Enter') {
      setSearchQuery(query);
    }
  };

  const onSuggestionClicked = (suggestion: string) => {
    setSearchQuery(suggestion);
    clearSuggestions();
  };

  return (
    <div>
      <InputGroup className="mb-3">
        <Form.Control
          placeholder="Potato AND Chips..."
          aria-label="Potato AND Chips..."
          value={query}
          onChange={onQueryChanged}
          onKeyDown={onKeyDown}
        />
        <SuggestionList
          suggestionResult={suggestionResult}
          onSuggestionClicked={onSuggestionClicked}
        />
        <Button variant="outline-secondary" onClick={onSearchClicked}>
          Search
        </Button>
      </InputGroup>
    </div>
  );
};
