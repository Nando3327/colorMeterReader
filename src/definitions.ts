export interface ReaderInterfacePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  listPairedDevices(): { devices: any[] }
  connect(options: { value: string }): Promise<{ value: boolean }>
  disconnect(options: { value: string }): Promise<{ value: boolean }>
}
