import React, { useEffect, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { appendQueryParams } from '../../../../utils/query-params';
import { Button, Form, InputGroup } from 'react-bootstrap';
import { SuggestionList } from '../../suggestion';
import { useSuggestions } from '../../../../lib/hooks/useSuggestions';

export const SearchPanel = () => {
  const navigate = useNavigate();
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
    doSearch();
  };

  const onKeyDown = (event: any) => {
    if (event.key === 'Enter') {
      doSearch();
    }
  };

  const onSuggestionClicked = (suggestion: string) => {
    setQuery(suggestion);
    clearSuggestions();
  };

  const doSearch = () => {
    searchParams.set('query', query);
    navigate(appendQueryParams('/', searchParams.toString()));
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
          onSuggestionClicked={onSuggestionClicked}
          suggestionResult={suggestionResult}
        />
        <Button variant="outline-secondary" onClick={onSearchClicked}>
          Search
        </Button>
      </InputGroup>
    </div>
  );
};
