/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.IO;
using Thrift;
using Thrift.Collections;
using System.Runtime.Serialization;
using Thrift.Protocol;
using Thrift.Transport;


#if !SILVERLIGHT
[Serializable]
#endif
public partial class Inscriere : TBase
{
  private Participant _participant;
  private Proba _proba;

  public Participant Participant
  {
    get
    {
      return _participant;
    }
    set
    {
      __isset.participant = true;
      this._participant = value;
    }
  }

  public Proba Proba
  {
    get
    {
      return _proba;
    }
    set
    {
      __isset.proba = true;
      this._proba = value;
    }
  }


  public Isset __isset;
  #if !SILVERLIGHT
  [Serializable]
  #endif
  public struct Isset {
    public bool participant;
    public bool proba;
  }

  public Inscriere() {
  }

  public void Read (TProtocol iprot)
  {
    iprot.IncrementRecursionDepth();
    try
    {
      TField field;
      iprot.ReadStructBegin();
      while (true)
      {
        field = iprot.ReadFieldBegin();
        if (field.Type == TType.Stop) { 
          break;
        }
        switch (field.ID)
        {
          case 1:
            if (field.Type == TType.Struct) {
              Participant = new Participant();
              Participant.Read(iprot);
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 2:
            if (field.Type == TType.Struct) {
              Proba = new Proba();
              Proba.Read(iprot);
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          default: 
            TProtocolUtil.Skip(iprot, field.Type);
            break;
        }
        iprot.ReadFieldEnd();
      }
      iprot.ReadStructEnd();
    }
    finally
    {
      iprot.DecrementRecursionDepth();
    }
  }

  public void Write(TProtocol oprot) {
    oprot.IncrementRecursionDepth();
    try
    {
      TStruct struc = new TStruct("Inscriere");
      oprot.WriteStructBegin(struc);
      TField field = new TField();
      if (Participant != null && __isset.participant) {
        field.Name = "participant";
        field.Type = TType.Struct;
        field.ID = 1;
        oprot.WriteFieldBegin(field);
        Participant.Write(oprot);
        oprot.WriteFieldEnd();
      }
      if (Proba != null && __isset.proba) {
        field.Name = "proba";
        field.Type = TType.Struct;
        field.ID = 2;
        oprot.WriteFieldBegin(field);
        Proba.Write(oprot);
        oprot.WriteFieldEnd();
      }
      oprot.WriteFieldStop();
      oprot.WriteStructEnd();
    }
    finally
    {
      oprot.DecrementRecursionDepth();
    }
  }

  public override string ToString() {
    StringBuilder __sb = new StringBuilder("Inscriere(");
    bool __first = true;
    if (Participant != null && __isset.participant) {
      if(!__first) { __sb.Append(", "); }
      __first = false;
      __sb.Append("Participant: ");
      __sb.Append(Participant== null ? "<null>" : Participant.ToString());
    }
    if (Proba != null && __isset.proba) {
      if(!__first) { __sb.Append(", "); }
      __first = false;
      __sb.Append("Proba: ");
      __sb.Append(Proba== null ? "<null>" : Proba.ToString());
    }
    __sb.Append(")");
    return __sb.ToString();
  }

}

