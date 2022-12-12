import { SearchPanel } from '../../components/search/documents/SearchPanel';
import { DocumentResultList } from '../../components/search/documents/DocumentResultList';
import { Link } from 'react-router-dom';
import { useSearch } from '../../lib/hooks/useSearch';
import { FacetPanel } from '../../components/search/facet/FacetPanel';
import { SpellcheckerPanel } from '../../components/search/spellcheck/SpellcheckPanel';
import { IndexingDocumentPanel } from '../../components/indexing-documents/IndexingDocumentPanel';
import { Col, Container, Row, Stack } from 'react-bootstrap';
import { When } from 'react-if';

interface SidePanelSectionProps {
  sectionTitle: string;
  showSeparator?: boolean;
  children: JSX.Element;
}

const SidePanelSection = ({
  sectionTitle,
  showSeparator = true,
  children,
}: SidePanelSectionProps) => {
  return (
    <>
      <Stack gap={3}>
        <div className="text-center fs-5">{sectionTitle}</div>
        {children}
      </Stack>
      <When condition={showSeparator}>
        <hr />
      </When>
    </>
  );
};

export const DocumentSearch = () => {
  const { searchResult, loading } = useSearch();

  return (
    <Container fluid className="my-3">
      <Row className="w-50 m-auto">
        <SearchPanel />
        <SpellcheckerPanel spellcheckResult={searchResult?.spellcheckResult} />
      </Row>
      <Row>
        <Col md={2}>
          <Stack className="border p-3">
            <SidePanelSection sectionTitle="Indexing">
              <Link
                className="btn btn-outline-primary w-75 mx-auto"
                to="/index">
                Index web pages
              </Link>
            </SidePanelSection>
            <SidePanelSection sectionTitle="Faceting">
              <FacetPanel />
            </SidePanelSection>
            <SidePanelSection
              sectionTitle="Indexing documents"
              showSeparator={false}>
              <IndexingDocumentPanel />
            </SidePanelSection>
          </Stack>
        </Col>
        <Col md={6}>
          <DocumentResultList
            loading={loading}
            documents={searchResult?.documents}
          />
        </Col>
      </Row>
    </Container>
  );
};
