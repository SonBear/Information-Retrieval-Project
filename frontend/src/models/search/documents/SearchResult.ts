import { RetrievedDocument } from './RetrievedDocument';
import { SpellcheckResult } from '../spellcheck/SpellcheckResult';

export interface SearchResult {
  documents: RetrievedDocument[];
  spellcheckResult: SpellcheckResult;
}
