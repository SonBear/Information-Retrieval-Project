import { SearchPanel } from '../../components/search/documents/SearchPanel';
import { DocumentResultList } from '../../components/search/documents/DocumentResultList';
import { Link } from 'react-router-dom';
import { useSearch } from '../../lib/hooks/useSearch';
import { FacetPanel } from '../../components/search/facet/FacetPanel';
import { SpellcheckerPanel } from '../../components/search/spellcheck/SpellcheckPanel';
import { IndexingDocumentPanel } from '../../components/indexing-documents/IndexingDocumentPanel';
import { FirstDocumentPanel } from '../../components/search/documents/FirstDocumentPanel';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export const DocumentSearch = () => {
  const { searchResult, loading } = useSearch();

  return (
    <Container fluid style={{ padding: '2 rem', color: 'white' }}>
      <Row>
        <Link to="/index">Index web pages</Link>
      </Row>
      <Row>
        <div
          className="border-bottom mb-4 p-2"
          style={{ backgroundColor: '#2C2C2C' }}>
          <SearchPanel />
          <IndexingDocumentPanel />
        </div>
      </Row>
      <Row>
        <Col md={2}>
          <FacetPanel />
        </Col>

        <Col md={6}>
          <SpellcheckerPanel
            spellcheckResult={searchResult?.spellcheckResult}
          />
          <DocumentResultList
            loading={loading}
            documents={searchResult?.documents}
          />
        </Col>
        <Col md={4}>
          <FirstDocumentPanel document={searchResult?.documents[0]} />
        </Col>
      </Row>
    </Container>
  );
};
