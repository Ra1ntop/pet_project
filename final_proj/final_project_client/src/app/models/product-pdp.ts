import {ProductVariant} from "./product-variant";

export interface ProductPdp {
  id: number;
  cpu: string;
  name: string;
  description: string;
  ram: number;
  sizeScreen: string;
  camera: string;
  safety: string;
  battery: string;
  os: string;
  simCard: string;
  sensors: string;
  waterResistance: string;
  videoRecording: string;
  images: string[];
  variantDtos: ProductVariant[];


}
