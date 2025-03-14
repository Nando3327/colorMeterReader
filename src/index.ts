import { registerPlugin } from '@capacitor/core';

import type { ReaderInterfacePlugin } from './definitions';

const ReaderInterface = registerPlugin<ReaderInterfacePlugin>('ReaderInterface', {
  web: () => import('./web').then((m) => new m.ReaderInterfaceWeb()),
});

export * from './definitions';
export { ReaderInterface };
