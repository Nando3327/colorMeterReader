export interface ReaderInterfacePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  listPairedDevices(): Promise<{ devices: any[] }>
}
