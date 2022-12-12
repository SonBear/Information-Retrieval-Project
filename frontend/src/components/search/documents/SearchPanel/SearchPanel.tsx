import React, { useEffect, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { appendQueryParams } from '../../../../utils/query-params';
import logo from '../../../../logo.png';
import { Search } from 'react-bootstrap-icons';
import { Row } from 'react-bootstrap';
import { Col } from 'react-bootstrap';
import { Figure } from 'react-bootstrap';

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
    searchParams.set('query', query);
    navigate(appendQueryParams('/', searchParams.toString()));
  };

  const inputStyle = {
    width: '93%',
    backgroundColor: 'transparent',
    control: (base: any) => ({
      ...base,
      border: 0,
      // This line disable the blue border
      boxShadow: 'none',
    }),
  };

  const SearchBar = {
    width: '100%',
    padding: '5px',
    backgroundColor: '#303134',
  };

  const ButtonStyle = {
    backgroundColor: 'transparent',
  };

  return (
    <Row className=" align-self-center m-2">
      <Col md="1">
        <Figure>
          <Figure.Image src={logo}></Figure.Image>
        </Figure>
      </Col>
      <Col md="4">
        <div style={SearchBar} className="rounded">
          <input
            style={inputStyle}
            placeholder="Type something..."
            value={query}
            onChange={onQueryChanged}
            className="p-1 text-white border-0"
          />
          <button
            style={ButtonStyle}
            className="border-0"
            onClick={onSearchClicked}>
            <Search color="white" />
          </button>
        </div>
      </Col>
    </Row>
  );
};
