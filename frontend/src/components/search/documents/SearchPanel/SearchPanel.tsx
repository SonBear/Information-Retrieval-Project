import React, { useEffect, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { appendQueryParams } from '../../../../utils/query-params';
import { Button, Form, FormControlProps, InputGroup } from 'react-bootstrap';

export const SearchPanel = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const [query, setQuery] = useState('');

  useEffect(() => {
    const q = searchParams.get('query') || '';
    setQuery(q);
  }, [searchParams]);

  const onQueryChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = e.target.value;
    setQuery(newValue);
  };

  const onSearchClicked = () => {
    doSearch();
  };

  const onKeyDown = (event: any) => {
    if (event.key === 'Enter') {
      doSearch();
    }
  };

  const doSearch = () => {
    searchParams.set('query', query);
    navigate(appendQueryParams('/', searchParams.toString()));
  };

  return (
    <InputGroup className="mb-3">
      <Form.Control
        placeholder="Potato AND Chips..."
        aria-label="Potato AND Chips..."
        value={query}
        onChange={onQueryChanged}
        onKeyDown={onKeyDown}
      />
      <Button variant="outline-secondary" onClick={onSearchClicked}>
        Search
      </Button>
    </InputGroup>
  );
};
