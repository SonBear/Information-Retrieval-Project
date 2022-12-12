export enum SupportedLanguage {
  Spanish = 'es',
  English = 'en',
}

export const ReverseLanguageMapping: { [key: string]: SupportedLanguage } = {
  es: SupportedLanguage.Spanish,
  en: SupportedLanguage.English,
};
