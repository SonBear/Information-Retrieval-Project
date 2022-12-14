import { SpellcheckResult } from '../spellcheck/SpellcheckResult';
import { RetrievedDocument } from './RetrievedDocument';

export interface SearchResult {
  totalDocsFound: number;
  documents: RetrievedDocument[];
  spellcheckResult: SpellcheckResult;
}
