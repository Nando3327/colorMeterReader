export interface ReaderInterfacePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  reviewPermissions(): Promise<{ value: boolean }>;
  listPairedDevices(): { devices: any[] }
  connect(options: { value: string }): Promise<{ value: boolean }>
  disconnect(options: { value: string }): Promise<{ value: boolean }>
  valueDetected(): Promise<{ l: string, a: string, b: string }>
  isReaderConnected(): Promise<{ value: boolean }>
  getReaderCalibrationStatus(): Promise<{ black: boolean, white: boolean }>
  calibrateWhite(): Promise<{ calibrated: boolean }>
  calibrateBlack(): Promise<{ calibrated: boolean }>
  initNueServiceBle(): void
}
