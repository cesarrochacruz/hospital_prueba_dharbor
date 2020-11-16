import { NotaVisita } from './nota-visita';
export class Paciente {
    id:Number;
    hospitalId:Number;
    doctorId:Number;
    nombre:String;
    apellido:String;
    fechaNacimiento:Date;
    direccion:String;
    notaVisita:NotaVisita;
}