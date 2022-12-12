import { SpellcheckResult } from '../spellcheck/SpellcheckResult';
import { RetrievedDocument } from './RetrievedDocument';

export interface SearchResult {
  documents: RetrievedDocument[];
  spellcheckResult: SpellcheckResult;
}
