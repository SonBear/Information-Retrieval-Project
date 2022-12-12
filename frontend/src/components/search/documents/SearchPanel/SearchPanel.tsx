import React, { useEffect, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { appendQueryParams } from '../../../../utils/query-params';
import {
  Button,
  Form,
  FormControlProps,
  InputGroup,
  ListGroup,
} from 'react-bootstrap';

export const SearchPanel = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const [query, setQuery] = useState('');
  const [suggesstions, setSuggestions] = useState(['soy una sugerencia']);

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

  const suggestionStyle = {
    width: '100%',
    Position: 'absolute',
    cursor: 'pointer',
  };
  const createSuugestions = (suggestions: any) => {
    return (
      <ListGroup style={suggestionStyle}>
        {suggestions.map((a: any) => {
          return (
            <ListGroup.Item onClick={onClickSuggestion}>{a}</ListGroup.Item>
          );
        })}
      </ListGroup>
    );
  };

  const onClickSuggestion = (event: any) => {
    setQuery(event.target.firstChild.data);
    setSuggestions([]);
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
        {suggesstions.length > 0 && createSuugestions(suggesstions)}
        <Button variant="outline-secondary" onClick={onSearchClicked}>
          Search
        </Button>
      </InputGroup>
    </div>
  );
};
