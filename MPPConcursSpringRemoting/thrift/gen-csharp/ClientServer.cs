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
public partial class ClientServer : TBase
{
  private string _host;
  private int _port;

  public string Host
  {
    get
    {
      return _host;
    }
    set
    {
      __isset.host = true;
      this._host = value;
    }
  }

  public int Port
  {
    get
    {
      return _port;
    }
    set
    {
      __isset.port = true;
      this._port = value;
    }
  }


  public Isset __isset;
  #if !SILVERLIGHT
  [Serializable]
  #endif
  public struct Isset {
    public bool host;
    public bool port;
  }

  public ClientServer() {
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
            if (field.Type == TType.String) {
              Host = iprot.ReadString();
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 2:
            if (field.Type == TType.I32) {
              Port = iprot.ReadI32();
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
      TStruct struc = new TStruct("ClientServer");
      oprot.WriteStructBegin(struc);
      TField field = new TField();
      if (Host != null && __isset.host) {
        field.Name = "host";
        field.Type = TType.String;
        field.ID = 1;
        oprot.WriteFieldBegin(field);
        oprot.WriteString(Host);
        oprot.WriteFieldEnd();
      }
      if (__isset.port) {
        field.Name = "port";
        field.Type = TType.I32;
        field.ID = 2;
        oprot.WriteFieldBegin(field);
        oprot.WriteI32(Port);
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
    StringBuilder __sb = new StringBuilder("ClientServer(");
    bool __first = true;
    if (Host != null && __isset.host) {
      if(!__first) { __sb.Append(", "); }
      __first = false;
      __sb.Append("Host: ");
      __sb.Append(Host);
    }
    if (__isset.port) {
      if(!__first) { __sb.Append(", "); }
      __first = false;
      __sb.Append("Port: ");
      __sb.Append(Port);
    }
    __sb.Append(")");
    return __sb.ToString();
  }

}

