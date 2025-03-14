import { WebPlugin } from '@capacitor/core';

import type { ReaderInterfacePlugin } from './definitions';

export class ReaderInterfaceWeb extends WebPlugin implements ReaderInterfacePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
